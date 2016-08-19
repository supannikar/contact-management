package com.example.web;

import com.example.web.api.v1.transport.CISGroupTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.MediaType.TEXT_HTML_VALUE;

@Controller
public class GroupController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GroupController.class);

    @RequestMapping(value = "/api/cis/groups", method = RequestMethod.GET, produces = TEXT_HTML_VALUE)
    public String getGroupLisyPage() {
        LOGGER.info("Get Group List page");
        return "group";
    }

    @RequestMapping(value = "/api/cis/details", method = RequestMethod.GET, produces = TEXT_HTML_VALUE)
    public String getDetailListPage() {
        LOGGER.info("Get Detail List page");
        return "detail";
    }

    @RequestMapping(value = "/api/cis/groups/{id}/detail", method = RequestMethod.GET, produces = TEXT_HTML_VALUE)
    public String getDetailByGroup(Model model, @PathVariable Long id){
        model.addAttribute("id", id);
        return "detail";
    }

    @RequestMapping(value = "/api/cis/groupadd", method = RequestMethod.GET, produces = TEXT_HTML_VALUE)
    public String getAddGroup(Model model, @RequestParam(value = "id", required = false) Long id,
                              @RequestParam(value = "name", required = false) String name) {
        LOGGER.info("Get Add new group page");
        model.addAttribute("id", id);
        model.addAttribute("name", name);
        return "group_add";
    }

    @RequestMapping(value = "/api/cis/detailadd", method = RequestMethod.GET, produces = TEXT_HTML_VALUE)
    public String getAddDeatil(Model model, @RequestParam(value = "id", required = false) Long id,
                               @RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "email", required = false) String email,
                               @RequestParam(value = "phone", required = false) String phone,
                               @RequestParam(value = "groupid", required = false) String groupid) {
        LOGGER.info("Get Add new detail page");
        model.addAttribute("id", id);
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("phone", phone);
        model.addAttribute("groupid", groupid);
        return "detail_add";
    }
}
