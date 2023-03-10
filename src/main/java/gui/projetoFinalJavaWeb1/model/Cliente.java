package gui.projetoFinalJavaWeb1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@Table(name = "tb_cliente")
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Preenchimento obrigatório do nome!")
    @NotBlank(message = "Não pode ser vazio!")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatório do cpf!")
    @NotBlank(message = "Não pode ser vazio!")
    @Size(min = 11, message = "Cpf deve conter 11 caracteres!")
    @Column(unique = true)
    private String cpf;

    @NotEmpty(message = "Preenchimento obrigatório do endereço!")
    @NotBlank(message = "Não pode ser vazio!")
    private String endereco;

    @NotEmpty(message = "Preenchimento obrigatório do contato!")
    @NotBlank(message = "Não pode ser vazio!")
    private String contato;

    @NotEmpty(message = "Preenchimento obrigatório da senha!")
    @NotBlank(message = "Não pode ser vazio!")
    @Size(min = 8, message = "Senha deve conter no mínimo 8 caracteres!")
    private String senha;

}
