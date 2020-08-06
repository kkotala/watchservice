package pl.pzu;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WatchserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WatchserviceApplication.class, args);

        try {
            String path = "c://test";

            System.out.println(String.format( "Monitoring %s", path  ));
            WatchService watcher = FileSystems.getDefault().newWatchService();
            Path watchPath = FileSystems.getDefault().getPath(path);

            watchPath.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);

            while (true) {
                WatchKey key = watcher.take();
                for (WatchEvent<?> event: key.pollEvents()) {
                    Object context = event.context();
                    System.out.println( String.format( "Event %s, type %s", context, event.kind() ));
                }
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

}
