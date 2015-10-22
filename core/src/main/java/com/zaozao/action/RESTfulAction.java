package com.zaozao.action;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RESTfulAction {

    protected Logger logger = LoggerFactory.getLogger(RESTfulAction.class);

    @RequestMapping(value = "/hello", produces = "text/html;charset=UTF-8")
    public String hello() {
        return "你好！hello";  
    }  
  
    @RequestMapping(value = "/say/{msg}", produces = "application/json;charset=UTF-8")
    public String say(@PathVariable(value = "msg") String msg) {
        return "{\"msg\":\"you say:'" + msg + "'\"}";  
    }

    @RequestMapping(value = "/person/{id:\\d+}", method = RequestMethod.GET, produces = {"application/json", "application/xml", "text/html"})
    public String getPerson(@PathVariable("id") int id, Model model) {
        logger.info("获取人员信息id=" + id);
        Person person = new Person();  
        person.setName("张三");  
        person.setSex("男");  
        person.setAge(30);  
        person.setId(id);
        model.addAttribute("model", person);
        return "index";
    }  

    @RequestMapping(value = "/person/{id:\\d+}", method = RequestMethod.DELETE)
    public Object deletePerson(@PathVariable("id") int id) {
        logger.info("删除人员信息id=" + id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", "删除人员信息成功");
        return jsonObject;
    }

    @RequestMapping(value = "/person", method = RequestMethod.POST)
    public Object addPerson(Person person) {
        logger.info("注册人员信息成功id=" + person.getId());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", "注册人员信息成功");
        return jsonObject;
    }

    @RequestMapping(value = "/person", method = RequestMethod.PUT)
    public Object updatePerson(Person person) {
        logger.info("更新人员信息id=" + person.getId());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", "更新人员信息成功");
        return jsonObject;
    }

    @RequestMapping(value = "/person", method = RequestMethod.PATCH)
    public List<Person> listPerson(@RequestParam(value = "name", required = false, defaultValue = "") String name) {

        logger.info("查询人员name like " + name);
        List<Person> lstPersons = new ArrayList<Person>();

        Person person = new Person();
        person.setName("张三");
        person.setSex("男");
        person.setAge(25);
        person.setId(101);
        lstPersons.add(person);

        Person person2 = new Person();
        person2.setName("李四");
        person2.setSex("女");
        person2.setAge(23);
        person2.setId(102);
        lstPersons.add(person2);

        Person person3 = new Person();
        person3.setName("王五");
        person3.setSex("男");
        person3.setAge(27);
        person3.setId(103);
        lstPersons.add(person3);

        return lstPersons;
    }

    class Person {

        private int id;
        private int age;
        private String name;
        private String sex;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}  