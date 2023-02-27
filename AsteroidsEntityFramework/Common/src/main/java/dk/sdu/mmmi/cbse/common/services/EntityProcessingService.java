package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.serviceInterfaces.IEntityProcessingService;

import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

public class EntityProcessingService {
    private static EntityProcessingService service;
    private ServiceLoader<IEntityProcessingService> loader;

    private EntityProcessingService(){
        loader = ServiceLoader.load(IEntityProcessingService.class);
    }

    public static synchronized EntityProcessingService getInstance(){
        if (service == null){
            service = new EntityProcessingService();
        }

        return service;
    }

    public void processAll(GameData gameData, World world){
        try{
            for (IEntityProcessingService service : loader) {
                service.process(gameData, world);
            }
        }catch (ServiceConfigurationError serviceConfigurationError){
            serviceConfigurationError.printStackTrace();
        }

    }
}
