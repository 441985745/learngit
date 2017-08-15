document.domain='opple.com';
  function SetParameters(p,pSplit)
   { 
     var option={length:'200',title:'',help:'None'};
     properties=p.split(pSplit);
     for(i=0;i<properties.length;i++)
     {
            namevalue = properties[i].split(':');
            if(namevalue.length >1)
            {
                execute ="option." + namevalue[0] +'=\''+ namevalue[1]+'\';';
                eval(execute);
            }
     }
     return option;
   }


function CometClient(handler, publicToken, privateToken, onmessage, ontimeout, oncancelled, onerror)
{
    this.lastMessageId = 1000;
    this.publicToken = publicToken;
    this.privateToken = privateToken;
    this.handler = handler;
    this.onmessage = onmessage;
    this.ontimeout = ontimeout;
    this.oncancelled = oncancelled;
    this.onerror = onerror;
    this.running = true;
}

CometClient.prototype.stopWaiting = function()
{
    this.running = false;
}

CometClient.prototype.startWaiting = function()
{
    this.running = true;
    this.waitForMessages();
}

CometClient.prototype.waitForMessages = function()
{
    var me = this;

    $.ajax(
    {
        url: this.handler,
        type: "POST",
        data: { privateToken: this.privateToken, lastMessageId: this.lastMessageId },
        success: function(response)
        {  
            var messages =null;
            if(response!=null&&response!="")
            {
 
               messages =SetParameters(response,";");
            }        
            if (messages != null && messages.length > 0)
            {
                var keepWaiting = me.running;
                for (var i = 0; i < 1; i++)
                {
                    if (me.lastMessageId < messages.MessageId)
                        me.lastMessageId = messages.MessageId;

                    switch (messages.Name.toLowerCase())
                    {
                        case "timedout":
                             me.ontimeout();
                            break;
                        case "cancelled":
                             keepWaiting = false;
                            me.oncancelled();
                            break;
                        case "message":
                            me.onmessage(messages);
                            break;
                    }
                }
                if (keepWaiting)
                    me.waitForMessages();
            }
        },
        error: function()
        {
            me.onerror();
        }
    });
}

   