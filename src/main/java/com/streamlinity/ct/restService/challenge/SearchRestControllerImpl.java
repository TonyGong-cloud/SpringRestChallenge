package com.streamlinity.ct.restService.challenge;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RestController;

/*
 * This controller needs to expose the following rest endpoints.  You need to fill in the implementation here
 *
 * Required REST Endpoints
 *
 *      /item                       Get all items
 *      /item?category=C            Get all items in category specified by Category shortName
 *      /item/{itemShortName}       Get item that matches the specified Item shortName
 */

@Profile("default")
@RestController
public class SearchRestControllerImpl {
   @Autowired
    SearchSvcInterface searchSvcInterface;

    @GetMapping("item")
    public List<Item> getAllItems(){

        return searchSvcInterface.getItems();
    }

    @GetMapping(path = "item", params = "category")
    public List<Item> getItemByCategory(@RequestParam(name = "category") String category){

        return searchSvcInterface.getItems(category);

    }

    @GetMapping("item/{shortName}")
    public List<Item> getItemByShortName(@PathVariable String shortName){

            return searchSvcInterface.getItem(shortName);
    }
}
