package com.example.jdbc_module.repository;

import com.example.jdbc_module.Contact;
import com.example.jdbc_module.repository.mapper.ContactRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ContactRepositoryImpl implements ContactRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Contact> findAll() {
        return jdbcTemplate.query("SELECT * FROM contact", new ContactRowMapper());
    }

    @Override
    public Optional<Contact> findById(Long id) {
        String sql = "SELECT * FROM contact  WHERE id=?";
        Contact contact = DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new ArgumentPreparedStatementSetter(new Object[]{id}),
                        new RowMapperResultSetExtractor<>(new ContactRowMapper(), 1)));
        return Optional.ofNullable(contact);
    }

    @Override
    public Contact save(Contact contact) {
        contact.setId(System.currentTimeMillis());
        String sql = "INSERT INTO contact (id,firstName, lastName, email, phone) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql, contact.getId(), contact.getFirstName(), contact.getLastName(),
                contact.getEmail(), contact.getPhone());
        return contact;
    }

    @Override
    public Contact update(Contact contact) {
        Contact existedContact = findById(contact.getId()).orElse(null);
        if (existedContact != null) {
            String sql = "UPDATE contact SET firstName = ?, lastName = ?, email = ?, phone = ? WHERE id = ?";
            jdbcTemplate.update(sql, contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getPhone(), existedContact.getId());
        }
        return contact;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM contact WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
