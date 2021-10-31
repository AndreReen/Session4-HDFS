import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class testHadoop {

  @Test
  public void automationTask() throws Exception {
    String url = "hdfs://192.168.88.241";
    String hdfsReadPath = "/test/airlines.dat";
    String hdfsWritePath = url + "/user/avro";
    String localPath = "src/main/resources";
    String avroFileName = "airlines.avro";

    String airlinesCsv = Hdfs.readFileToString(url, hdfsReadPath);
    Assert.assertTrue(airlinesCsv.contains("Svyaz Rossiya"));

    List<Airline> airlinesList = (List<Airline>) Csv.csvToList(Airline.class, airlinesCsv);
    Assert.assertEquals(6162, airlinesList.size());
    Assert.assertEquals("Svyaz Rossiya", airlinesList.get(6161).getName());

    Avro.writeListToAvroFile(airlinesList, localPath, avroFileName);

    String avroOut = Hdfs.readFileToString(localPath, avroFileName);

    Hdfs.writeFileToHdfs(hdfsWritePath, avroFileName, avroOut);
  }
}
