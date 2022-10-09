package ccut.service.impl;

import ccut.common.CommonResponse;
import ccut.mapper.StoreAuditMapper;
import ccut.model.pojo.StoreAudit;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class StoreAuditServiceImplTest {

    @Autowired
    StoreAuditMapper storeAuditMapper;
    @Autowired
    StoreAuditServiceImpl storeAuditService;

    @Test
    //测试分页
    void getStoreAuditByStatus() {
        CommonResponse<HashMap<String, Object>> storeAuditByStatus = storeAuditService.getStoreAuditByStatus(1, 2, 3);
        List<StoreAudit> records =(List<StoreAudit>)storeAuditByStatus.getData().get("records");
        Object total = storeAuditByStatus.getData().get("total");
        records.forEach(System.out::println);
        System.out.println(total);
    }
    @Test
    void  pageTest(){
        LambdaQueryWrapper<StoreAudit> storeAuditLambdaQueryWrapper = new LambdaQueryWrapper<>();
        storeAuditLambdaQueryWrapper.eq(StoreAudit::getApprovalStatus,1);
        //逻辑分页分页   加上 false 就是物理分页
        Page<StoreAudit> Page = new Page<>(1,3,false);
        Page<StoreAudit> storeAuditPage = storeAuditMapper.selectPage(Page, storeAuditLambdaQueryWrapper);

        List<StoreAudit> records = storeAuditPage.getRecords();
        //总页数
        long total1 = Page.getTotal();
        //当前分页大小对应的页数
        long pages1 = Page.getPages();
        //当前页数
        long current = Page.getCurrent();
        //当前页条数
        long size = Page.getSize();
        System.out.println("total1 :"+total1);
        System.out.println("pages1 :"+pages1);
        System.out.println("current :"+current);
        System.out.println("size :"+size);

        records.forEach(System.out::println);
    }

    @Test
    public void selectMapsPage(){
        LambdaQueryWrapper<StoreAudit> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.select(StoreAudit::getApprovalStatus);
        Page<Map<String , Object>> mapPage = new Page<>(1 , 2 , false);
        IPage<Map<String , Object>> mapIPage = storeAuditMapper.selectMapsPage(mapPage , userLambdaQueryWrapper);
        System.out.println("总页数： "+mapIPage.getPages());
        System.out.println("总记录数： "+mapIPage.getTotal());
        mapIPage.getRecords().forEach(System.out::println);
    }
}