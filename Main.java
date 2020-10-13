import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ThreadGroup downloads = new ThreadGroup("Downloads");
        while(true){
            Scanner sc = new Scanner(System.in);
            String command = sc.next();
            if(command.equals("download")){
                System.out.println("Enter download link : ");
                String downloadLink = sc.next();
                System.out.println("Enter file name : ");
                String fileName = sc.next();
                System.out.println("Enter download path : ");
                String downloadPath = sc.next();
                Thread thread = new Thread(downloads, new Downloader(fileName, downloadLink, downloadPath), fileName);
                thread.start();
            }
            if(command.equals("downloads")){
                downloads.list();
            }
            if(command.equals("exit")){
                System.out.println("All downloads will be stop, continue ? (y/n) : ");
                String answer = sc.next();
                if(answer.equals("y")) {
                    System.out.println("All downloads stopping...");
                    downloads.stop();
                    break;
                }
                if(answer.equals("n")){
                    continue;
                }
                else{
                    System.out.println("Wrong answer.");
                }
            }
            else{
                System.out.println("Undefined command.");
            }
        }
    }
}
