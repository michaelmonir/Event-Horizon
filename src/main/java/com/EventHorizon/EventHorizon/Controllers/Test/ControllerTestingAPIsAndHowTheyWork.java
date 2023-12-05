package com.EventHorizon.EventHorizon.Controllers.Test;

import com.EventHorizon.EventHorizon.DTOs.Test.ADTO;
import com.EventHorizon.EventHorizon.Exceptions.AdsOptionExceptions.AdsOptionNotFoundException;
import org.springframework.web.bind.annotation.*;

/*
When sending Dto's you just send it in the request body and it will be converted directly to the json you want as the examples below.
However, this doesn't go with the GetMapping, and also you should make it the body itself not inside the body.
So if you are expecting an event object the whole body will be an event, not just a field or parameter in the json.
If you are sending multiple jsons then you can create one class called Wrapper and wrap all the jsons in one class and receive it as a body,
Or you can just simply use a list<ParentDTO>, but this case doesn't occur frequently.
Also you can send multiple other fields with the body as path variables.
Nested DTOs are detected by spring boot and you don't have to handle them
 */

@RestController
@RequestMapping("/test")
public class ControllerTestingAPIsAndHowTheyWork
{
    // It is not normal for get mapping to do a request body
    @GetMapping("/{id}")
    public int testGet(@PathVariable int id) {
        return id;
    }

    @PostMapping("/")
    public ADTO testPost(@RequestBody ADTO adto) {
        System.out.println(adto.id);
        System.out.println(adto.bdto.id);
        return adto;
    }

    @PutMapping("/{id}")
    public ADTO testPut(@PathVariable int id, @RequestBody ADTO adto) {
        System.out.println(id);
        System.out.println(adto.id);
        System.out.println(adto.bdto.id);
        return adto;
    }

    @PutMapping("/{id}/{name}")
    public ADTO testPutWithTwoParameters(@PathVariable int id, @PathVariable String name, @RequestBody ADTO adto) {
        System.out.println(id);
        System.out.println(name);
        System.out.println(adto.id);
        System.out.println(adto.bdto.id);
        return adto;
    }

    @DeleteMapping("/{id}")
    public ADTO testDelete(@PathVariable int id, @RequestBody ADTO adto) {
        System.out.println(id);
        System.out.println(adto.id);
        System.out.println(adto.bdto.id);
        return adto;
    }
}

