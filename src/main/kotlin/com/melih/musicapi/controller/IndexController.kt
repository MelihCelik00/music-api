package com.melih.musicapi.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.view.RedirectView

@Controller
class IndexController {

    @RequestMapping(value = ["/"], method = [RequestMethod.GET])
    fun redirectToSwagger(): RedirectView {
        return RedirectView("/swagger-ui.html")
    }
}