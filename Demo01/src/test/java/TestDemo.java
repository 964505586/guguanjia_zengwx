import com.dfbz.config.SpringMybatisConfig;
import com.dfbz.mapper.AppVersionMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatisConfig.class)
public class TestDemo {

    @Autowired
    SpringMybatisConfig springMybatisConfig;

    @Autowired
    SqlSessionFactoryBean sqlSessionFactoryBean;

    @Autowired
    DataSource dataSource;

    @Autowired
    AppVersionMapper appVersionMapper;

    @Test
    public void test3() {
        System.out.println(appVersionMapper.selectAll());
    }

    @Test
    public void test() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void test2() {
        try {
            System.out.println(sqlSessionFactoryBean.getObject().openSession().getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
