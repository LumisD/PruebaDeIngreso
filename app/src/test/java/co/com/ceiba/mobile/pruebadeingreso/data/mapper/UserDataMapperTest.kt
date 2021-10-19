package co.com.ceiba.mobile.pruebadeingreso.data.mapper

import co.com.ceiba.mobile.pruebadeingreso.factory.ModelFactory
import org.junit.Assert
import org.junit.Test

class UserDataMapperTest {

    private val mapper = UserDataMapper()

    @Test
    fun `given UserEntry, when fromDataToDomain, then return UserModel`() {

        val entry = ModelFactory.makeUserEntry()
        val model = with(mapper) { entry.fromDataToDomain() }

        Assert.assertEquals(entry.id, model.id)
        Assert.assertEquals(entry.name, model.name)
        Assert.assertEquals(entry.email, model.email)
        Assert.assertEquals(entry.phone, model.phone)
    }

    @Test
    fun `given UserEntity, when fromEntityToDomain, then return UserModel`() {

        val entity = ModelFactory.makeUserEntity()
        val model = with(mapper) { entity.fromEntityToDomain() }

        Assert.assertEquals(entity.id, model.id)
        Assert.assertEquals(entity.name, model.name)
        Assert.assertEquals(entity.email, model.email)
        Assert.assertEquals(entity.phone, model.phone)
    }

    @Test
    fun `given UserModel, when fromDomainToEntity, then return UserEntity`() {

        val model = ModelFactory.makeUserModel()
        val entity = with(mapper) { model.fromDomainToEntity() }

        Assert.assertEquals(entity.id, model.id)
        Assert.assertEquals(entity.name, model.name)
        Assert.assertEquals(entity.email, model.email)
        Assert.assertEquals(entity.phone, model.phone)
    }

}