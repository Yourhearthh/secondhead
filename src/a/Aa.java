package a;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Properties;


public class Aa<T> {
  private Properties prop = new Properties();// 创建一个配置文件的对象
  private String clzName;

  public Aa(Class<? extends Object> clz) {
    try {
      // Class<? extends Object> clz = obj.getClass();// 获得传入参数的对象类型（实体PO/VO对象类型）
      this.clzName = clz.getSimpleName();// 获得类名，该类名用于orm映射文件的文件名
      String ormFileName = this.clzName + ".orm";// 生成orm的配置文件名

      URL urlPath = clz.getResource(".");// 获取实体类的路径，该路径用于检索orm文件
      String filePath = URLDecoder.decode(urlPath.getPath(), "utf-8");// 需要对得到的URL解码，避免乱码问题

      String fileFullPath = filePath + ormFileName;// 拼接orm映射文件的完整路径
      System.out.println("ORM文件路径：" + fileFullPath);

      File file = new File(fileFullPath);// 构建文件对象
      FileInputStream inStream = new FileInputStream(file);// 以文件输入流的形式打开文件
      this.prop.load(inStream);// 加载配置文件
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void add(T obj) {
    // 构建查询语句
    // 以sms数据库的StudentInfo表为例
    StringBuffer sb = new StringBuffer();
    StringBuffer sbVal = new StringBuffer();// 用于拼接字段对应的"?"数
    // sb.setLength(0);// 字符串归零

    sb.append("insert into ");
    sb.append(this.prop.getProperty(this.clzName));
    sb.append(" (");
    String colNamePK = this.prop.getProperty("PrimaryKey");
    sb.append(colNamePK);
    sbVal.append("?");

    Field[] fields = obj.getClass().getDeclaredFields();
    for (Field field : fields) {
      String fName = field.getName();// 实体类的属性名
      String colName = this.prop.getProperty(fName);// 表的字段名
      if (!"serialVersionUID".equals(fName) && !colNamePK.equals(colName)) {
        // System.out.println(fName);
        sb.append(",");
        sb.append(colName);
        sbVal.append(",?");
      }
    }
    // sb.append("stuNumber,stuName");
    // sb.append(",fk_ClassInfo_id");
    sb.append(") values (");
    // sb.append("?,?");
    // sb.append(",?");
    sb.append(sbVal);
    sb.append(")");
    sb.append(";");
    System.out.println(sb.toString());
  }

  public static void main(String[] args) {
//    Aa<StudentInfo> aa = new Aa<StudentInfo>(StudentInfo.class);
//    aa.add(new StudentInfo());
  }
}
