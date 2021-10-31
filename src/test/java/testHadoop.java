import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class testHadoop {

  @Test
  public void automationTask() throws Exception {
    String url = "hdfs://192.168.88.240";
    String localPath = "src/main/resources/";
    String avroFileName = "airlines.avro";

    Hdfs.writeFileToHdfs(url + "/test", "airlines.dat", localPath + "airlines.dat");

    String airlinesCsv = Hdfs.readFileToString(url, "test/airlines.dat");
    Assert.assertTrue(airlinesCsv.contains("Svyaz Rossiya"));

    List<Airline> airlinesList = (List<Airline>) Csv.csvToList(Airline.class, airlinesCsv);
    Assert.assertEquals(6162, airlinesList.size());
    Assert.assertEquals("Svyaz Rossiya", airlinesList.get(6161).getName());

    Avro.writeListToAvroFile(airlinesList, localPath, avroFileName);
    Hdfs.writeFileToHdfs(url + "/avro", avroFileName, localPath + avroFileName);
  }
}
