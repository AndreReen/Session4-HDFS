import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class Hdfs {

  public static void writeFileToHdfs(String ip, String fileName, String fileContent)
      throws IOException {
    String uri = ip + "/" + fileName;
    Configuration conf = new Configuration();
    FileSystem fs = FileSystem.get(URI.create(uri), conf);

    Path hdfsWritePath = new Path(uri);

    FSDataOutputStream outputStream = fs.create(hdfsWritePath);
    outputStream.writeChars(fileContent);
    outputStream.close();
  }

  public static String readFileToString(String ip, String fileName) throws IOException {
    String uri = ip + "/" + fileName;
    Configuration conf = new Configuration();
    FileSystem fs = FileSystem.get(URI.create(uri), conf);

    try (FSDataInputStream in = fs.open(new Path(uri))) {
      return org.apache.commons.io.IOUtils.toString(in, StandardCharsets.UTF_8);
    }
  }

}

