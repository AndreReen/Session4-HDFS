import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class Hdfs {

  public static void writeFileToHdfs(String ip, String fileName, String readFile)
      throws IOException {
    String uri = ip + "/" + fileName;
    Configuration conf = new Configuration();
    FileSystem fs = FileSystem.get(URI.create(uri), conf);

    Path hdfsWritePath = new Path(uri);

    BufferedReader reader = new BufferedReader(new FileReader(readFile));

    FSDataOutputStream outputStream = fs.create(hdfsWritePath);
    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,StandardCharsets.UTF_8));
    IOUtils.copy(reader, bufferedWriter);
    bufferedWriter.close();
    reader.close();
    fs.close();
  }

  public static String readFileToString(String ip, String fileName) throws IOException {
    String uri = ip + "/" + fileName;
    Configuration conf = new Configuration();
    FileSystem fs = FileSystem.get(URI.create(uri), conf);

    try (FSDataInputStream in = fs.open(new Path(uri))) {
      return org.apache.commons.io.IOUtils.toString(in, StandardCharsets.UTF_8);
    }
  }

  @Deprecated
  public static void writeCharsToHdfs(String ip, String fileName, String fileContent)
      throws IOException {
    String uri = ip + "/" + fileName;
    Configuration conf = new Configuration();
    FileSystem fs = FileSystem.get(URI.create(uri), conf);

    Path hdfsWritePath = new Path(uri);

    FSDataOutputStream outputStream = fs.create(hdfsWritePath);
    outputStream.writeChars(fileContent);
    outputStream.close();
  }
}

