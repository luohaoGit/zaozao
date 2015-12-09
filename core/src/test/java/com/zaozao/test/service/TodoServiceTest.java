package com.zaozao.test.service;

import com.zaozao.test.base.BaseJunit4Test;
import com.zaozao.utils.HttpClientUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2015/2/11.
 */
public class TodoServiceTest {
    class MyCallable implements Callable<Object> {
        private String taskNum;

        MyCallable(String taskNum) {
            this.taskNum = taskNum;
        }

        public Object call() throws Exception {
            System.out.println(">>>" + taskNum + "任务启动");
            Date dateTmp1 = new Date();
            HttpClientUtil.httpPost("http://file.zxyq.com.cn:7093/api.php/Home/Analyze/getKpSchScoreAbility.html?" +
                    "areaid=110201&userid=1022&paperid=04b833a441921&informid=04b833a441921&userRole=5&gradeid=8&subjectid=3&urltype=2&schid=2060",null);
            Date dateTmp2 = new Date();
            long time = dateTmp2.getTime() - dateTmp1.getTime();
            System.out.println(">>>" + taskNum + "任务终止");
            return taskNum + "任务返回运行结果,当前任务时间【" + time + "毫秒】";
        }
    }

    @Test
    public void createTodo(){
        System.out.println("----程序开始运行----");
        Date date1 = new Date();

        int taskSize = 50;
        // 创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);
        // 创建多个有返回值的任务
        List<Future> list = new ArrayList<Future>();
        for (int i = 0; i < 1000; i++) {
            Callable c = new MyCallable(i + " ");
            // 执行任务并获取Future对象
            Future f = pool.submit(c);
            // System.out.println(">>>" + f.get().toString());
            list.add(f);
        }
        // 关闭线程池
        pool.shutdown();

        // 获取所有并发任务的运行结果
        for (Future f : list) {
            // 从Future对象上获取任务的返回值，并输出到控制台
            try {
                System.out.println(">>>" + f.get().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        Date date2 = new Date();
        System.out.println("----程序结束运行----，程序运行时间【"
                + (date2.getTime() - date1.getTime()) + "毫秒】");


    }

    @Test
    public void getTodoById(){

    }

    @Test
    public void getAllTodos(){

    }


}
