
import com.webmagic.dao.DealExcelPropertyDao;
import com.webmagic.dao.FundHkDao;
import com.webmagic.model.DealExcelProperty;
import com.webmagic.model.FundHk;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {
    private SqlSessionFactory sqlSessionFactory;
    @Before
    public void before(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-mybatis.xml");
        sqlSessionFactory = (SqlSessionFactory)applicationContext.getBean("sqlSessionFactory");

    }

    @Test
    public void test(){
        //id,GGRQ,JZRQ,DWJZ,DWJZBZ,MXZDWZCJZ,MXZDWZCJZBZ,MXZDWJZSJXJ,MXZDWJZSJXJBZ,FXFEZS
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        FundHkDao fundHkDao = sqlSession.getMapper(FundHkDao.class);
        FundHk fundHk = new FundHk();

        fundHk.setJJJYDM("12345");
        fundHk.setDWJZ("213131231231231");
        fundHk.setDWJZBZ("1");
        fundHk.setMXZDWZCJZBZ("1");
        fundHk.setMXZDWJZSJXJ("1");
        fundHk.setMXZDWJZSJXJBZ("1");
        fundHk.setFXFEZS("1");
        fundHk.setDWJZBZ("1");
        fundHk.setBZ("FDSFVSGVFDVG");
        fundHkDao.insertFundHk(fundHk);



    }

    @Test
    public void test2(){
        //QSname,QSnum,GPJE,GPJEZB,QSJE,QSJEZB,ZQJE,ZQJEZB,HGJE,HGJEZB,QZJE,QZJEZB,JJJE,JJJEZB,QHJE,QHJEZB
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        DealExcelPropertyDao dealExcelPropertyDao = sqlSession.getMapper(DealExcelPropertyDao.class);
        DealExcelProperty dealExcelProperty = new DealExcelProperty();

        dealExcelProperty.setJJZDM(123456);
        dealExcelProperty.setQSname("213213");
        dealExcelProperty.setQSnum(1);

        dealExcelPropertyDao.insertFundJYXW(dealExcelProperty);
    }

}
