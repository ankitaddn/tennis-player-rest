package io.datajek.springdata.tennisplayer.tennisplayerrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Tennis Player API";
    }

    @GetMapping("/players")
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/players/{id}")
    public Player getPlayer(@PathVariable int id) {
        return playerService.getPlayerById(id);
    }

    @PostMapping("/players")
    public Player addPlayer(@RequestBody Player p) {
        p.setId(0);
        return playerService.addPlayer(p);
    }

    @PutMapping("/players/{id}")
    public Player updatePlayerDetails(@PathVariable int id, @RequestBody Player p) {
        return playerService.updatePlayer(id, p);
    }

    @PatchMapping("/players/{id}")
    public Player patchPlayerUpdate(@PathVariable int id, @RequestBody Map<String, Object> pMap) {
        return playerService.patchUpdatePlayer(id, pMap);
    }

    @PatchMapping("/players/{id}/titles")
    public void patchUpdateTitles(@PathVariable int id, @RequestBody int titles) {
        playerService.updateTitles(id, titles);
    }

    @DeleteMapping("/players/{id}")
    public String deletePlayer(@PathVariable int id){
        return playerService.deletePlayer(id);
    }
}
