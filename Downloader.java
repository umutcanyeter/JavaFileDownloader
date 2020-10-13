import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import java.io.*;

public class Downloader implements Runnable {
    private String downloadName;
    private String downloadLink;
    private String downloadPath;

    public Downloader(String downloadName, String downloadLink, String downloadPath) {
        this.downloadName = downloadName;
        this.downloadLink = downloadLink;
        this.downloadPath = downloadPath;
    }

    public void Download() throws IOException {
        HttpClient client = new HttpClient();
        GetMethod get = new GetMethod(downloadLink);
        get.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.75 Safari/535.7");
        client.executeMethod(get);
        InputStream in = get.getResponseBodyAsStream();
        FileOutputStream out = new FileOutputStream(new File(downloadPath + "\\" + downloadName));
        byte[] b = new byte[1024];
        int len = 0;
        while ((len = in.read(b)) != -1) {
            out.write(b, 0, len);
        }
        in.close();
        out.close();
        System.out.println("Download " + downloadName + " completed.");
    }

    @Override
    public void run() {
        try {
            Download();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
