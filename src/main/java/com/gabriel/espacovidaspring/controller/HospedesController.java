package com.gabriel.espacovidaspring.controller;

/* Fala pro spring que tem um end point
//@RestController
// End point
//@RequestMapping("/api/hospedes")
//@AllArgsConstructor
public class HospedesController {


    private final HospedesRepository hospedesRepository;

    // @RequestMapping(method = RequestMethod.GET) faz a mesma coisa do de baixo
    @GetMapping
    public List<Hospedes> listHospedes() {
        return hospedesRepository.findAll();
    }

    // O spring mapeia automaticamente
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Hospedes newHospede(@RequestBody Hospedes hospede) {
        System.out.println(hospede);
        return hospedesRepository.save(hospede);
        // return ResponseEntity.status(HttpStatus.CREATED).body(hospedesRepository.save(hospede));
    }   

    @DeleteMapping(value = "{id}", produces = "application/json")
    public ResponseEntity<Resposta> deleteHospede(@PathVariable("id") long id) {
        
        Resposta resposta = new Resposta(0, "message", "errorMessageDefault");
        try {
            hospedesRepository.deleteById(id);

            resposta.setCode(200);
            resposta.setMessage("Deletado com sucesso");
            
            return ResponseEntity.status(HttpStatus.OK).body(resposta);
        } catch (Exception e) {

            resposta.setCode(404);
            resposta.setMessage("Hospede nao encontrado");
            resposta.setErrorMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
        

    }
}*/
