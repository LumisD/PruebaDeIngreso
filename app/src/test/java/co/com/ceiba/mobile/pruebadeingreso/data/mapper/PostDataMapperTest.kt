package co.com.ceiba.mobile.pruebadeingreso.data.mapper

import co.com.ceiba.mobile.pruebadeingreso.factory.ModelFactory
import org.junit.Assert
import org.junit.Test

class PostDataMapperTest {

    private val mapper = PostDataMapper()

    @Test
    fun `given PostEntry, when fromDataToDomain, then return PostModel`() {

        val entry = ModelFactory.makePostEntry()
        val model = with(mapper) { entry.fromDataToDomain() }

        Assert.assertEquals(entry.id, model.id)
        Assert.assertEquals(entry.userId, model.userId)
        Assert.assertEquals(entry.title, model.title)
        Assert.assertEquals(entry.body, model.body)
    }

    @Test
    fun `given PostEntity, when fromEntityToDomain, then return PostModel`() {

        val entity = ModelFactory.makePostEntity()
        val model = with(mapper) { entity.fromEntityToDomain() }

        Assert.assertEquals(entity.id, model.id)
        Assert.assertEquals(entity.userId, model.userId)
        Assert.assertEquals(entity.title, model.title)
        Assert.assertEquals(entity.body, model.body)
    }

    @Test
    fun `given PostModel, when fromDomainToEntity, then return PostEntity`() {

        val model = ModelFactory.makePostModel()
        val entity = with(mapper) { model.fromDomainToEntity() }

        Assert.assertEquals(entity.id, model.id)
        Assert.assertEquals(entity.userId, model.userId)
        Assert.assertEquals(entity.title, model.title)
        Assert.assertEquals(entity.body, model.body)
    }

}