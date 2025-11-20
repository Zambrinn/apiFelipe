package trabalhoFelipe.github.Zambrinn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import trabalhoFelipe.github.Zambrinn.model.DTOs.UserRequest;
import trabalhoFelipe.github.Zambrinn.model.DTOs.UserResponse;
import trabalhoFelipe.github.Zambrinn.model.User;
import trabalhoFelipe.github.Zambrinn.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserResponse createUser(UserRequest request) { // Uso o retorno (Response)
        // e no parametro dos métodos eu uso o que eu preciso para criar (Request)
        User user = User.builder() // uso o builder para criar o usuário
                .name(request.name()) // coloco no nome do usuário o que foi pedido no request
                .email(request.email()) // coloco no email do usuário o que foi pedido no request
                .password(request.password()) // coloco no email do usuário o que foi pedido no request
                .createdAt(LocalDateTime.now())
                .build(); // crio o usuário

        User savedUser = userRepository.save(user);
        // User savedUser: O tipo da variável é a classe User, depois dou um nome para a variável
        // (savedUser), depois puxo do repositório o metodo de salvar e passo a variável inicial ali em cima.
        return convertUserToDTO(savedUser); // Esse convertUserToDTO é um método
    }

    public List<UserResponse> getAllUsers() { // Esse método usa uma Lista das Responses
        return userRepository.findAll() // Puxo do repositório o método findAll
                .stream() // Esse stream é uma implementação do java para listas
                .map(this::convertUserToDTO) // esse map é para filtrar
                // esse this:: é para puxarmos da CLASSE que estamos, o método convertUserToDTO
                .collect(Collectors.toList()); // Transformo dnv numa lista
    }

    public UserResponse updateUser(UUID id, UserRequest request) {
        // Para o método de atualizar o usuário, primeiro precisamos passar
        // o id no parâmetro para buscarmos o usuário pelo id para mudarmos
        // especificamente ele
        User existingUser = userRepository.findById(id) // Busco no repositorio o método findByID
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com o id" + id));
        // Esse "OrELseThrow" é: se não der certo a requisição de procurar por id
        // O que eu faço? Ai eu coloquei pra lançar uma exceção
        existingUser.setName(request.name()); // Atualizo o nome
        existingUser.setEmail(request.email()); // Atualizo o email
        existingUser.setPassword(request.password()); // Atualizo a senha

        User updatedUser = userRepository.save(existingUser);
        // Salvo numa variável o usuário atualizado

        return convertUserToDTO(updatedUser);
        // retorno o usuário atualizado depois de passar ele no método de conversão
    }

    public void deleteUser(UUID id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não existe usuário com o id" + id));

        userRepository.delete(existingUser);
    }


    public UserResponse convertUserToDTO(User user) {
        return new UserResponse(
                user.getId(),
                user.getCreatedAt()
        );
    }
}
