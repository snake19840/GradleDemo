package com.example.demo.model;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="t_roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="roleid")
    private Integer roleid;
    @Column(name="rolename")
    private String rolename;
    @Column(name="age")
    private Integer age;
    @Column(name="address")
    private String address;
    @OneToMany(mappedBy = "roles")
    private Set<MysqlUser> mysqlUsers=new HashSet<>();

    //fetch = FetchType.EAGER: 查询时加入立即加载或是配置长时间的session
    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    //@JoinTable : 映射中间表
    //joinColumns: 当前表中的主键所关联的中间表的外键
    @JoinTable(name = "t_roles_menus",joinColumns = @JoinColumn(name = "roles_id"),inverseJoinColumns = @JoinColumn(name = "menus_id"))
    private  Set<Menus> menus=new HashSet<>();


    public Set<Menus> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menus> menus) {
        this.menus = menus;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public Set<MysqlUser> getMysqlUsers() {
        return mysqlUsers;
    }

    public void setMysqlUsers(Set<MysqlUser> mysqlUsers) {
        this.mysqlUsers = mysqlUsers;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    @Override
    public String toString() {
        return "Roles{" +
                "roleid=" + roleid +
                ", rolename='" + rolename + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }

    public Roles() {
        super();
    }
}
