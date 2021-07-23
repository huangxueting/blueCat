package listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.blueCat.entity.BlueCatDemo;
import com.blueCat.entity.BlueCatExcelVo;
import com.blueCat.service.BlueCatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

// 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
public class BlueCatExcelVoListener extends AnalysisEventListener<BlueCatDemo> {
    private static final Logger LOGGER = LoggerFactory.getLogger(BlueCatExcelVoListener.class);

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<BlueCatDemo> list = new ArrayList<>();

    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    private BlueCatService blueCatService;

    public BlueCatExcelVoListener() {
        // 这里是demo，所以随便new一个。实际使用如果到了spring,请使用下面的有参构造函数
//        this.blueCatService = new BlueCatService();
    }

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param blueCatService
     */
    public BlueCatExcelVoListener(BlueCatService blueCatService) {
        this.blueCatService = blueCatService;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param blueCatExcelVo
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(BlueCatDemo blueCatExcelVo, AnalysisContext context) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(blueCatExcelVo));
        list.add(blueCatExcelVo);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            blueCatService.save(list);
            // 存储完成清理 list
            list.clear();
        }

    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        blueCatService.save(list);
        LOGGER.info("所有数据解析完成！");
    }
}
