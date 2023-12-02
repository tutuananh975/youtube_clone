package personal.project.youtube.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import personal.project.youtube.entity.Channel;
import personal.project.youtube.entity.Role;
import personal.project.youtube.entity.User;
import personal.project.youtube.repository.RoleRepository;
import personal.project.youtube.service.RoleService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role save(Role entity) {
        return roleRepository.save(entity);
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }
}
