package com.music.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MusicController
{
    @Autowired private MusicService service;

    @GetMapping("/music")
    public String showMusicList(Model model)
    {
        List<musicapp>listMusics = service.listAll();
        model.addAttribute("listMusics",listMusics);

        return "musics";
    }

    @GetMapping("/music/new")
    public String showNewForm(Model model)
    {
        model.addAttribute("music",new musicapp());
        model.addAttribute("pageTitle","Add Music");
        return "music_form";
    }

    @PostMapping("/music/save")
    public String saveMusic(musicapp music)
    {
        service.save(music);

        return "redirect:/music";
    }

    @GetMapping("/music/edit/{Music_ID}")
    public String showEditForm(@PathVariable("Music_ID") Integer Music_ID,Model model)
    {
        try {
            musicapp music = service.get(Music_ID);
            model.addAttribute("music",music);
            model.addAttribute("pageTitle","Edit Music (Music_ID: "+Music_ID+")");
            return "music_form";
        } catch (MusicNotFoundException e)
        {
            System.out.println("Music Id not found");
            return "redirect:/music";
        }
    }

    @GetMapping("/music/delete/{Music_ID}")
    public String deleteMusic(@PathVariable("Music_ID") Integer Music_ID)
    {
        try
        {
            service.delete(Music_ID);
        }
        catch (MusicNotFoundException e)
        {
            System.out.println("Music ID not found");
        }
        return "redirect:/music";
    }
}
