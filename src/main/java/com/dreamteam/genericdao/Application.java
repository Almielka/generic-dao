package com.dreamteam.genericdao;

import com.dreamteam.genericdao.dao.impl.CompanyDaoImpl;
import com.dreamteam.genericdao.domain.Company;

/**
 * Using generic DAO
 *
 * @author Anna S. Almielka
 */

public class Application {

    public static void main(String[] args) {

        CompanyDaoImpl companyDao = new CompanyDaoImpl();
        Company chrome = new Company("Chrome");
        Company firefox = new Company("Firefox");
        companyDao.create(chrome);
        companyDao.create(firefox);
        System.out.println("==== Create Chrome, Firefox, read all ====");
        System.out.println(companyDao.readAll());
        System.out.println("==== Read id = 1, id = 3 ====");
        System.out.println(companyDao.read(1L));
        System.out.println(companyDao.read(3L));
        chrome.setName("ChromeUpdated");
        companyDao.update(chrome);
        System.out.println("==== Update Chrome -> ChromeUpdated, read all ====");
        System.out.println(companyDao.readAll());
        companyDao.delete(firefox);
        System.out.println("==== Delete Firefox, read all ====");
        System.out.println(companyDao.readAll());

    }

}
