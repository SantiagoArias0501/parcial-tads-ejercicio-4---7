package co.edu.umanizales.tads.controller.dto;

import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.ListSE;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.service.ListSEService;
import co.edu.umanizales.tads.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/ListSE")
public class ListSEController {
    @Autowired
    private ListSEService listSEService;

    @Autowired
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getKids() {
        return new ResponseEntity<>(new ResponseDTO(
                200, listSEService.getKids(), null), HttpStatus.OK);
    }

    @GetMapping("/invert")
    public ResponseEntity<ResponseDTO> invert() {
        listSEService.invert();
        return new ResponseEntity<>(new ResponseDTO(
                200, "Se ha invertido la lista",
                null), HttpStatus.OK);
    }
    @GetMapping(path= "/change_extremes")
    public ResponseEntity<ResponseDTO>ChangeExtremes(){
        listSEService.changeExtremes();
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se ha intercambiado los extremos"
                ,null),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addKid(@RequestBody KidDTO kidDTO) {
        Location location = locationService.getLocationByCode(kidDTO.getCodeLocation());
        if (location == null) {
            return new ResponseEntity<>(new ResponseDTO(
                    404, "la ubicacion no existe", null)
                    , HttpStatus.OK);
        }
        listSEService.add(
                new Kid(kidDTO.getIdentification(),
                        kidDTO.getName(), kidDTO.getAge(),
                        kidDTO.getGender(), location));
        return new ResponseEntity<>(new ResponseDTO(
                200, "Se ha adicionado el Kid",
                null), HttpStatus.OK);
    }

    @GetMapping(path = "/kidsbylocations/{age}")
    public ResponseEntity<ResponseDTO> getKidsByLocation() {
        List<KidsByLocationDTO> kidsByLocationDTOList = new ArrayList<>();
        for (Location loc : locationService.getLocations()) {
            int count = listSEService.getCountKidsBylocationsCode(loc.getCode());
            int male = listSEService.getCountKidsBylocationsCode(loc.getCode());
            int female = listSEService.getCountKidsBylocationsCode(loc.getCode());
            if (count > 0) {
                kidsByLocationDTOList.add(new KidsByLocationDTO(loc,female,male,count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(
                200, kidsByLocationDTOList,
                null), HttpStatus.OK);
    }

    @GetMapping(path = "/kidsbyDepartament")
    public ResponseEntity<ResponseDTO> getKidsByDepartament() {
        List<KidsByLocationDTO> kidsByLocationDTOList = new ArrayList<>();
        for (Location loc : locationService.getLocationsByCodeSize(5)) {
            int count = listSEService.getCountKidsBylocationsCode(loc.getCode());
            int male = listSEService.getCountKidsBylocationsCode(loc.getCode());
            int female = listSEService.getCountKidsBylocationsCode(loc.getCode());
        if (count > 0) {
            kidsByLocationDTOList.add(new KidsByLocationDTO(loc,male,female, count));
        }
    }
    return new ResponseEntity<>(new ResponseDTO(
            200,kidsByLocationDTOList,null)
    ,HttpStatus.OK);
}


    @GetMapping(path = "/deleteKidbyAge")
    public ResponseEntity<ResponseDTO> deleteKidbyAge() {
        return new ResponseEntity<>(new ResponseDTO(200,"el niño fue eliminado",null ),HttpStatus.OK);
    }

    @GetMapping(path = "/deletebyage/{age}")
    public ResponseEntity<ResponseDTO> DeleteByAge(@PathVariable byte age){
        listSEService.deleteKidbyAge(age);
        return new ResponseEntity<>(new ResponseDTO(200,"niños eliminados ", null), HttpStatus.OK);

    }
    @GetMapping(path = "/gainpositionkid")
    public ResponseEntity<ResponseDTO>GainPositionKid(String id , int Advance){
        listSEService.gainPositionKid(id, Advance);
        return new ResponseEntity<>(new ResponseDTO(200,"el niño ah ganado posicion",null),HttpStatus.OK);
    }
}
