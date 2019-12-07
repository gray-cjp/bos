package com.cjp.action.Impl;

import com.cjp.domain.Region;
import com.cjp.domain.Subarea;
import com.cjp.service.SubareaService;
import com.cjp.utils.Customer;
import com.cjp.utils.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
@Scope("prototype")
public class SubareaAction extends IBaseActionImpl<Subarea> {
    @Resource
    private SubareaService subareaService;
    public String add(){
//        Subject subject = SecurityUtils.getSubject();
//        subject.checkPermission("staff-delete");
        System.out.println(subareaService);
        subareaService.add(model);
        return "list";
    }
    private String decidedzoneId;

    public void setDecidedzoneId(String decidedzoneId) {
        this.decidedzoneId = decidedzoneId;
    }

    public String pageQuery(){
        DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
        String addresskey = model.getAddresskey();
        if (StringUtils.isNotBlank(addresskey)){
            detachedCriteria.add(Restrictions.like("addresskey","%"+addresskey+"%"));
        }
        Region region = model.getRegion();
        if (region != null){
            String province = region.getProvince();
            String city = region.getCity();
            String district = region.getDistrict();
            detachedCriteria.createAlias("region","r");
            if (StringUtils.isNotBlank(province)){
                detachedCriteria.add(Restrictions.like("r.province","%"+province+"%"));
            }
            if(StringUtils.isNotBlank(city)){
                //添加过滤条件，根据市模糊查询-----多表关联查询，使用别名方式实现
                //参数一：分区对象中关联的区域对象属性名称
                //参数二：别名，可以任意
                detachedCriteria.add(Restrictions.like("r.city", "%"+city+"%"));
            }
            if(StringUtils.isNotBlank(district)){
                //添加过滤条件，根据区模糊查询-----多表关联查询，使用别名方式实现
                //参数一：分区对象中关联的区域对象属性名称
                //参数二：别名，可以任意
                detachedCriteria.add(Restrictions.like("r.district", "%"+district+"%"));
            }
        }
        subareaService.pageQuery(pageBean);
        //在 hbm 中把延迟加载关了 ，  因为延迟加载的代理对象转json 过于复杂无法转
        java2Json(pageBean,new String[]{"currentPage","detachedCriteria","pageSize","decidedzone","subareas"});
        return NONE;
    }
    public String exportXls() throws IOException {
        //获取分区数据
        List<Subarea> list = subareaService.findAll();
        //创建xls
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("分区数据");
        HSSFRow headrow = sheet.createRow(0);
        headrow.createCell(0).setCellValue("分区编号");
        headrow.createCell(1).setCellValue("开始编号");
        headrow.createCell(2).setCellValue("结束编号");
        headrow.createCell(3).setCellValue("位置信息");
        headrow.createCell(4).setCellValue("省市区");
        for (Subarea subarea : list){
            HSSFRow dateRow = sheet.createRow(sheet.getLastRowNum() + 1);
            dateRow.createCell(0).setCellValue(subarea.getId());
            dateRow.createCell(1).setCellValue(subarea.getStartnum());
            dateRow.createCell(2).setCellValue(subarea.getEndnum());
            dateRow.createCell(3).setCellValue(subarea.getPosition());
            dateRow.createCell(4).setCellValue(subarea.getRegion().getName());
        }
        //使用输出流进行文件下载（一个流、两个头）
        String filename="分区数据.xls";
        String mimeType = ServletActionContext.getServletContext().getMimeType(filename);
        ServletOutputStream outputStream = ServletActionContext.getResponse().getOutputStream();
        ServletActionContext.getResponse().setContentType(mimeType);
        //获取客户端浏览器类型
        String agent = ServletActionContext.getRequest().getHeader("User-Agent");
        filename = FileUtils.encodeDownloadFilename(filename,agent);
        ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename="+filename);
        workbook.write(outputStream);
        return NONE;
    }
    public String listajax(){
        List<Subarea> list = subareaService.findListNotAssociation();
        java2Json(list,new String[]{"subareas"});
        return NONE;
    }
    public String findListByDecidedzoneId(){
        List<Subarea> list =subareaService.findListByDecidedzoneId(decidedzoneId);
        java2Json(list,new String[]{"decidedzone","subareas"});
        return NONE;
    }
}
