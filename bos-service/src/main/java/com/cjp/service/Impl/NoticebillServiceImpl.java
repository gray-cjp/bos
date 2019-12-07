package com.cjp.service.Impl;

import com.cjp.dao.DecidedzoneDao;
import com.cjp.dao.NoticebillDao;
import com.cjp.dao.WorkbillDao;
import com.cjp.domain.*;
import com.cjp.service.NoticebillService;
import com.cjp.utils.BOSUtlis;
import com.cjp.utils.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
@Service
@Transactional
public class NoticebillServiceImpl implements NoticebillService {
    @Autowired
    private NoticebillDao noticebillDao;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private DecidedzoneDao decidedzoneDao;
    @Autowired
    private WorkbillDao workbillDao;
    @Override
    public void save(Noticebill noticebill) {
        User user = BOSUtlis.getLoginUser();
        noticebill.setUser(user);
        noticebillDao.save(noticebill);
        String pickaddress = noticebill.getPickaddress();
        String id = customerService.findDecidedzoneIdByAddress(pickaddress);
        if (id != null){
            Decidedzone decidedzone = decidedzoneDao.findById(id);
            Staff staff = decidedzone.getStaff();
            noticebill.setStaff(staff);
            noticebill.setOrdertype(Noticebill.ORDERTYPE_AUTO);
            Workbill workbill = new Workbill();
            workbill.setAttachbilltimes(0);
            workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));
            workbill.setNoticebill(noticebill);
            workbill.setPickstate(Workbill.PICKSTATE_NO);
            workbill.setRemark(noticebill.getRemark());
            workbill.setType(Workbill.TYPE_1);
            workbillDao.save(workbill);
            //调用短信平台，发送短信
        }else {
            noticebill.setOrdertype(Noticebill.ORDERTYPE_MAN);
        }

    }
}
