package com.streamlinity.ct.restService.challenge;

import com.streamlinity.ct.model.Item;

import java.io.File;
import java.util.List;

/*
 * Provide your implementation of the SearchSvcImpl here.
 * Also annotate your methods with Rest end point wrappers as required in the problem statement
 *
 * You can create any auxiliary classes or interfaces in this package if you need them.
 *
 * Do NOT add annotations as a Bean or Component or Service.   This is being handled in the custom Config class
 * PriceAdjustConfiguration
 */

public class SearchSvcImpl implements SearchSvcInterface {
    @Autowired
    ApplicationContext applicContex;
    
    List<Item> its;
    ObjectMapper oma = new ObjectMapper();
    
    
    @Override
    public void init(String itPJFileName) {
try {
            File jF = applicContex.getResource(itPJFileName).getFile();
            init(jF);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(File itPJFileName) {
try{
            items = oma.readValue(itPJFileName, new TypeReference<List<Item>>(){});
        }catch(JsonParseException e) {
            e.printStackTrace();
        }catch(JsonMappingException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Item> getItems() {
        return its;
    }

    @Override
    public List<Item> getItems(String category) {
        List<Item> res = new ArrayList<>();
        items.forEach(ele -> res.add(ele));
        return res.stream().filter(e -> category.equals(getSubstring(e))).collect(Collectors.toList());
    }

    @Override
    public List<Item> getItem(String itemShortName) {
        List<Item> result = new ArrayList<>();
        items.forEach( ele -> res.add(ele));
        return res.stream().filter(ele->itemShortName.equals(ele.getShort_name())).collect(Collectors.toList());
    }
}
