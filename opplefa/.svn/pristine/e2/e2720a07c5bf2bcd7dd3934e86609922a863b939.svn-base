document.domain='opple.com';
function($) {
	// settings
	$.meta = {
	  type: "class",
	  name: "data",
	  setType: function(type,name){
	    this.type = type;
	    this.name = name;
	  },
	  cre: /({.*})/,
	  single: 'data'
	};
	
	// reference to original setArray()
	var setArray = $.fn.setArray;
	
	// define new setArray()
	$.fn.setArray = function(arr){
	    return setArray.apply( this, arguments ).each(function(){
	      if ( this.nodeType == 9 || $.isXMLDoc(this) || this.metaDone ) return;
	      
	      var data = "{}";
	      
	      if ( $.meta.type == "class" ) {
	        var m = $.meta.cre.exec( this.className );
	        if ( m )
	          data = m[1];
	      } else if ( $.meta.type == "elem" ) {
	      	if( !this.getElementsByTagName ) return;
	        var e = this.getElementsByTagName($.meta.name);
	        if ( e.length )
	          data = $.trim(e[0].innerHTML);
	      } else if ( this.getAttribute != undefined ) {
	        var attr = this.getAttribute( $.meta.name );
	        if ( attr )
	          data = attr;
	      }
	      
	      if ( !/^{/.test( data ) )
	        data = "{" + data + "}";
	
	      eval("data = " + data);
	
	      if ( $.meta.single )
	        this[ $.meta.single ] = data;
	      else
	        $.extend( this, data );
	      
	      this.metaDone = true;
	    });
	};
	
	/**
	 * Returns the metadata object for the first member of the jQuery object.
	 *
	 * @name data
	 * @descr Returns element's metadata object
	 * @type jQuery
	 * @cat Plugins/Metadata
	 */
	$.fn.data = function(){
	  return this[0][$.meta.single || "data"];
	};
})(jQuery);