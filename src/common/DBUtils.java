package common;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {

    // ��ʾ�������ݿ���û���
    private static String USERNAME = "root";
    // �������ݿ������
    private static String PASSWORD = "root";
    // �������ݿ��������Ϣ
    private static String DRIVER = "com.mysql.jdbc.Driver";
    // ����������ݿ�ĵ�ַ
    private static String URL = "jdbc:mysql://localhost:3306/mytestforgrab";

    private static DBUtils per = null;
    // �������ݿ������
    private Connection con = null;
    // ����sql����ִ�ж���
    private PreparedStatement pstmt = null;
    // �����ѯ���صĽ������
    private ResultSet resultSet = null;

    private DBUtils() {

    }

    /**
     * ����ģʽ����ù������һ������
     * 
     * @return
     */
    public static DBUtils getInstance() {
        if (per == null) {
            per = new DBUtils();
            per.registeredDriver();
        }
        return per;
    }

    private void registeredDriver() {
        // TODO Auto-generated method stub
        try {
            Class.forName(DRIVER);
            System.out.println("ע�������ɹ���");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * ������ݿ������
     * 
     * @return
     */
    public Connection getConnection() {
        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("�������ݿ�ɹ�!!");
        return con;
    }

    /**
     * ��ɶ����ݿ�ı�����ɾ�����޸ĵĲ���
     * 
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public boolean executeUpdate(String sql, List<Object> params)
            throws SQLException {

        boolean flag = false;

        int result = -1;  // ��ʾ���û�ִ�����ɾ�����޸ĵ�ʱ����Ӱ�����ݿ������

        pstmt = con.prepareStatement(sql);

        if (params != null && !params.isEmpty()) {
            int index = 1;
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }

        result = pstmt.executeUpdate();
        flag = result > 0 ? true : false;

        return flag;
    }

    /**
     * �����ݿ��в�ѯ����
     * 
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public List<Map<String, Object>> executeQuery(String sql,
            List<Object> params) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int index = 1;
        pstmt = con.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }
        resultSet = pstmt.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int cols_len = metaData.getColumnCount();
        while (resultSet.next()) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < cols_len; i++) {
                String cols_name = metaData.getColumnName(i + 1);
                Object cols_value = resultSet.getObject(cols_name);
                if (cols_value == null) {
                    cols_value = "";
                }
                map.put(cols_name, cols_value);
            }
            list.add(map);
        }
        return list;

    }

    /**
     * jdbc�ķ�װ�����÷����������װ,�Ѵ����ݿ��л�ȡ�����ݷ�װ��һ����Ķ�����
     * 
     * @param sql
     * @param params
     * @param cls
     * @return
     * @throws Exception
     */
    public <T> List<T> executeQueryByRef(String sql, List<Object> params,
            Class<T> cls) throws Exception {
        List<T> list = new ArrayList<T>();
        int index = 1;
        pstmt = con.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }
        resultSet = pstmt.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int cols_len = metaData.getColumnCount();
        while (resultSet.next()) {
            T resultObject = cls.newInstance();  // ͨ��������ƴ���ʵ��
            for (int i = 0; i < cols_len; i++) {
                String cols_name = metaData.getColumnName(i + 1);
                Object cols_value = resultSet.getObject(cols_name);
                if (cols_value == null) {
                    cols_value = "";
                }
                Field field = cls.getDeclaredField(cols_name);
                field.setAccessible(true); // ��javabean�ķ���privateȨ��
                field.set(resultObject, cols_value);
            }
            list.add(resultObject);
        }
        return list;

    }

    public void closeDB() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    

}