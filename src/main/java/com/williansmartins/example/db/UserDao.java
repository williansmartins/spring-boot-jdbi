package com.williansmartins.example.db;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    public Handle getHandle(){
        Connection conn =  DataSourceUtils.getConnection(dataSource);
        return DBI.open(conn);
    }

    public List<UserBean> list(){
        Handle handle = getHandle();
        UserSQLs userQLs = handle.attach(UserSQLs.class);
        return userQLs.list();
    }

    public Integer insert(UserBean item){
        Handle handle = getHandle();
        UserSQLs userSQLs = handle.attach(UserSQLs.class);
        return userSQLs.insert(item);
    }

    @RegisterMapper(UserMapper.class)
    interface UserSQLs {
        @SqlQuery("select * from users")
        List<UserBean> list();

        @SqlUpdate("insert into users (username)" +
                    " values(:username) ")
        @GetGeneratedKeys
        Integer insert(@BindBean UserBean test);
    }

    public static class UserMapper implements ResultSetMapper<UserBean> {
        @Override
        public UserBean map(int i, ResultSet r, StatementContext statementContext) throws SQLException {
            UserBean bean = new UserBean();
            bean.setId((Integer) r.getObject("id"));
            bean.setUsername(r.getString("username"));
            return bean;
        }
    }

}
