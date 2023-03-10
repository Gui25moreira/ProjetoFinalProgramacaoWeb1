package gui.projetoFinalJavaWeb1.controller;

import gui.projetoFinalJavaWeb1.model.Cliente;
import gui.projetoFinalJavaWeb1.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Binding;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes")
    public ModelAndView clientes(
            @RequestParam(defaultValue ="1", value = "page") Integer numeroPagina,
            @RequestParam(defaultValue ="3", value = "size") Integer tamanhoPagina
        ) {

        ModelAndView modelAndView = new ModelAndView("clientes");
        Page<Cliente> clientesPage = this.clienteService.listarPaginado(numeroPagina-1,3);
        modelAndView.addObject("clientes", clientesPage.getContent());
        modelAndView.addObject("totalPages", clientesPage.getTotalPages());
        modelAndView.addObject("currentPage", numeroPagina);
        modelAndView.addObject("pageSize", clientesPage.getSize());


        return modelAndView;
    }

    @GetMapping("/cliente/add")
    public String addCliente(Model model, Cliente cliente){
        model.addAttribute("add", Boolean.TRUE);
        model.addAttribute("cliente", Objects.nonNull(cliente) ? cliente : new Cliente());
        return "cliente-add";
    }

    @PostMapping("/cliente/add")
    public String criarCliente(@Valid @ModelAttribute("cliente") Cliente cliente,
                               BindingResult result,
                               Model model){
        if(result.hasErrors()){
            return addCliente(model,cliente);
        }

        this.clienteService.createCliente(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/cliente/{clienteId}/delete")
    public String deletarCliente(@PathVariable("clienteId") Long id){
        this.clienteService.removerClientPorId(id);
        return "redirect:/clientes";
    }

    @GetMapping("/cliente/{clienteId}/edit")
    public String editarClientePorId(Model model, @PathVariable("clienteId") Long clienteId){
        Optional<Cliente> optionalCliente = this.clienteService.buscarClientePorId(clienteId);
        optionalCliente.ifPresent(cliente -> model.addAttribute("cliente", cliente));
        model.addAttribute("add", Boolean.FALSE);
        return "cliente-add";
    }

    @PutMapping("/cliente/{clienteId}/edit")
    public String editarCliente(@ModelAttribute("cliente") Cliente cliente,
                                @PathVariable("clienteId") Long clienteId) {
        cliente.setId(clienteId);
        this.clienteService.createCliente(cliente);
        return "redirect:/clientes";
    }

}