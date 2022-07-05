package de.bwaldvogel.mongo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.bwaldvogel.mongo.backend.memory.MemoryBackend;

public class InMemoryMongoServer extends MongoServer {

    private static final Logger log = LoggerFactory.getLogger(InMemoryMongoServer.class);

    public static void main(String[] args) {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 27017;
        final MongoServer mongoServer = new InMemoryMongoServer();
        mongoServer.bind("localhost", port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("shutting down {}", mongoServer);
            mongoServer.shutdownNow();
        }));
    }

    /**
     * creates a mongo server with in-memory backend
     */
    public InMemoryMongoServer() {
        super(new MemoryBackend());
    }
}
