package co.edu.umanizales.tads.controller.dto;

import co.edu.umanizales.tads.model.ListSE;
import co.edu.umanizales.tads.service.ListSEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/ListSE")
public class ListSEController {
    @Autowired
    private ListSEService listSEService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getKids() {
        return new ResponseEntity<>(new ResponseDTO(
                200, listSEService.getKids(), null), HttpStatus.OK);
    }

    @GetMapping(path = "/addkid")
    public ResponseEntity<ResponseDTO> addKid() {
        return new ResponseEntity<>(new ResponseDTO(
                200, listSEService.getKids(), null), HttpStatus.OK);
    }

    @GetMapping(path = "/deleteKidbyAge")
    public ResponseEntity<ResponseDTO> deleteKidbyAge() {
        return new ResponseEntity<>(new ResponseDTO(200,"el niño fue eliminado",null ),HttpStatus.OK);
    }

    @GetMapping(path = "/deletebyage")
    public ResponseEntity<ResponseDTO> deletebyage(byte age){
        listSEService.DeleteByAge(age);
        return new ResponseEntity<>(new ResponseDTO(200,"niños eliminados ", null), HttpStatus.OK);

    }
    @GetMapping(path = "/advanceposition")
    public ResponseEntity<ResponseDTO>advanceposition(String id , int Advance){
        listSEService.AdvancePosition(id, Advance);
        return new ResponseEntity<>(new ResponseDTO(200,"el niño ah ganado posicion",null),HttpStatus.OK);
    }
}
