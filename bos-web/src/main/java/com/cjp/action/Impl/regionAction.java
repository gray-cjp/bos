package com.cjp.action.Impl;

import com.cjp.domain.PageBean;
import com.cjp.domain.Region;
import com.cjp.service.RegionService;
import com.cjp.utils.PinYin4jUtils;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Controller
@Scope("prototype")
public class regionAction extends IBaseActionImpl<Region> {
    private File regionFile;
    private String q;

    public void setQ(String q) {
        this.q = q;
    }
    private List<Region> regionList = new ArrayList<Region>();
   @Autowired
   private RegionService regionService;
    public void setRegionFile(File regionFile) {
        this.regionFile = regionFile;
    }
    public String importXls() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(regionFile));
        HSSFSheet sheet = workbook.getSheetAt(0);
        for (Row rows : sheet){
            int rownumber = rows.getRowNum();
            if (rownumber == 0){
                continue;
            }
            System.out.println();
            String id= rows.getCell(0).getStringCellValue();
            String province= rows.getCell(1).getStringCellValue();
            String city= rows.getCell(2).getStringCellValue();
            String district= rows.getCell(3).getStringCellValue();
            String postcode= rows.getCell(4).getStringCellValue();
            Region region = new Region(id, province, city, district, postcode, null, null, null);
            province = province.substring(0,province.length()-1);
            city = city.substring(0,city.length()-1);
            district = district.substring(0,district.length()-1);
            String[] info = PinYin4jUtils.getHeadByString((province + city + district));
            String shortcode = StringUtils.join(info);
            String citycode = PinYin4jUtils.hanziToPinyin(shortcode, "");
            region.setShortcode(shortcode);
            region.setCitycode(citycode);
            regionList.add(region);
        }
        regionService.saveBatch(regionList);
        return NONE;
    }
    public String pageQuery(){
        regionService.pageQuery(pageBean);
        java2Json(pageBean,new String[]{"currentPage","detachedCriteria","pageSize","subareas"});
        return NONE;
    }
    public String listjax(){
        List<Region> regionList = null;
        if (StringUtils.isNotBlank(q)){
          regionList =  regionService.findListByQ(q);
        } else {
            regionList = regionService.findAll();
        }
        java2Json(regionList,new String[]{"subareas"});
        return NONE;
    }
}
