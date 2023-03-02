package io.datajek.springdata.tennisplayer.tennisplayerrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }

    public Player getPlayerById(int id){
        Optional<Player> player = playerRepository.findById(id);
        if(player.isPresent())
            return player.get();
        else
            throw new PlayerNotFoundException("Player with id {" + id + "} not found");
    }

    public Player addPlayer(Player p){
        return playerRepository.save(p);
    }

    public Player updatePlayer(int id, Player player){
        Optional<Player> playerRecord = playerRepository.findById(id);
        if(!playerRecord.isPresent())
            throw new PlayerNotFoundException("Player with id {" + id + "} not found");
        return playerRepository.save(player);
    }

    public Player patchUpdatePlayer(int id, Map<String, Object> playerPatch){
        Optional<Player> playerRecord = playerRepository.findById(id);
        if(playerRecord.isPresent()){
            playerPatch.forEach((key,value)->{
                Field field = ReflectionUtils.findField(Player.class,key);
                ReflectionUtils.makeAccessible(field);
                ReflectionUtils.setField(field, playerRecord.get(),value);
            });
        }else{
            throw new PlayerNotFoundException("Player with id {" + id + "} not found");
        }
        return playerRepository.save(playerRecord.get());
    }

    @Transactional
    public void updateTitles(int id, int titles){
        Optional<Player> playerRecord = playerRepository.findById(id);
        if(!playerRecord.isPresent())
            throw new PlayerNotFoundException("Player with id {" + id + "} not found");
        playerRepository.updateTitles(id,titles);
    }

    public String deletePlayer(int id){
       try {
           playerRepository.deleteById(id);
       }catch (Exception e){
           throw new PlayerNotFoundException("Player with id {" + id + "} not found");
       }
        return "Deleted player with id" + id;
    }


}
