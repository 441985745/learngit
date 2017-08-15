package com.opple.fa.myTasks.dao;

import com.opple.fa.myTasks.entity.MyTasks;
import com.opple.util.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhangshulong on 2017/2/28.
 */
public interface MyTasksDAO {
    List<MyTasks> searchMyTasksList(@Param("pager") Pager<MyTasks> pager, @Param("myTasks") MyTasks myTasks);
    Long searchMyTasksListCounts(@Param("myTasks") MyTasks myTasks);
    List<MyTasks> searchMyTasksList1(@Param("pager") Pager<MyTasks> pager, @Param("myTasks") MyTasks myTasks);
    Long searchMyTasksListCounts1(@Param("myTasks") MyTasks myTasks);
    List<MyTasks> searchMyTasksList2(@Param("pager") Pager<MyTasks> pager, @Param("myTasks") MyTasks myTasks);
    Long searchMyTasksListCounts2(@Param("myTasks") MyTasks myTasks);
    List<MyTasks> searchMyTasksList3(@Param("pager") Pager<MyTasks> pager, @Param("myTasks") MyTasks myTasks);
    Long searchMyTasksListCounts3(@Param("myTasks") MyTasks myTasks);
    List<MyTasks> searchMyTasksList4(@Param("pager") Pager<MyTasks> pager, @Param("myTasks") MyTasks myTasks);
    Long searchMyTasksListCounts4(@Param("myTasks") MyTasks myTasks);
    List<MyTasks> searchMyTasksList5(@Param("pager") Pager<MyTasks> pager, @Param("myTasks") MyTasks myTasks);
    Long searchMyTasksListCounts5(@Param("myTasks") MyTasks myTasks);
    List<MyTasks> searchMyTasksList6(@Param("pager") Pager<MyTasks> pager, @Param("myTasks") MyTasks myTasks);
    Long searchMyTasksListCounts6(@Param("myTasks") MyTasks myTasks);
    List<MyTasks> searchMyTasksList7(@Param("pager") Pager<MyTasks> pager, @Param("myTasks") MyTasks myTasks);
    Long searchMyTasksListCounts7(@Param("myTasks") MyTasks myTasks);
    List<MyTasks> searchMyTasksList8(@Param("pager") Pager<MyTasks> pager, @Param("myTasks") MyTasks myTasks);
    Long searchMyTasksListCounts8(@Param("myTasks") MyTasks myTasks);
    List<MyTasks> searchMyTasksList9(@Param("pager") Pager<MyTasks> pager, @Param("myTasks") MyTasks myTasks);
    Long searchMyTasksListCounts9(@Param("myTasks") MyTasks myTasks);
    List<MyTasks> searchMyTasksList10(@Param("pager") Pager<MyTasks> pager, @Param("myTasks") MyTasks myTasks);
    Long searchMyTasksListCounts10(@Param("myTasks") MyTasks myTasks);
    List<MyTasks> searchMyTasksList11(@Param("pager") Pager<MyTasks> pager, @Param("myTasks") MyTasks myTasks);
    Long searchMyTasksListCounts11(@Param("myTasks") MyTasks myTasks);
}
