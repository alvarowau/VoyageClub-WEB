package com.voyageclub.dao;

import com.voyageclub.model.Usuario;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(Arquillian.class)
public class BaseDAOTest {

    @Inject
    private BaseDAO<Usuario, Long> usuarioDAO;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(BaseDAO.class)
                .addClass(Usuario.class)
                .addAsManifestResource("META-INF/persistence-test.xml", "persistence.xml");
    }

    @Test
    public void testSaveAndGetById() {
        Usuario usuario = new Usuario("Alvaro", "Bajo", "Tabero", "alvarobajo893@gmail.com", "12345678", "123456");
        usuarioDAO.save(usuario);

        Usuario retrievedUser = usuarioDAO.getById(usuario.getId()).orElse(null);
        Assert.assertNotNull(retrievedUser);
        Assert.assertEquals(usuario.getNombre(), retrievedUser.getNombre());
    }

    // Agrega más pruebas según sea necesario
}
