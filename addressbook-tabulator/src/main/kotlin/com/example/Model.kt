package com.example

import kotlinx.browser.localStorage
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import org.w3c.dom.get
import org.w3c.dom.set
import pl.treksoft.kvision.state.ObservableList
import pl.treksoft.kvision.state.observableListOf

@Serializable
data class Address(
    val id: Int? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val email: String? = null,
    val favourite: Boolean? = false
)

fun Address.match(search: String?): Boolean {
    return search?.let {
        firstName?.contains(it, true) ?: false ||
                lastName?.contains(it, true) ?: false ||
                email?.contains(it, true) ?: false
    } ?: true
}

object Model {
    private var counter = 1

    val addresses: ObservableList<Address> = observableListOf(
        Address(counter++, "John", "Smith", "john.smith@mail.com", true),
        Address(counter++, "Karen", "Kowalsky", "kkowalsky@mail.com", true),
        Address(counter++, "William", "Gordon", "w.gordon@mail.com", false)
    )

    fun addAddress(address: Address) {
        addresses.add(address.copy(id = counter++))
        storeAddresses()
    }

    fun findAddress(id: Int): Address? {
        return addresses.find { it.id == id }
    }

    fun delAddress(id: Int) {
        addresses.find { it.id == id }?.let {
            addresses.remove(it)
            storeAddresses()
        }
    }

    fun saveAddress(id: Int, address: Address) {
        val index = addresses.map { it.id }.indexOf(id)
        if (index >= 0) {
            addresses[index] = address.copy(id = id)
            storeAddresses()
        }
    }

    private fun storeAddresses() {
        val jsonString = Json.encodeToString(ListSerializer(Address.serializer()), addresses)
        localStorage["addressesTabulator"] = jsonString
    }

    fun loadAddresses() {
        localStorage["addressesTabulator"]?.let {
            addresses.clear()
            Json.decodeFromString(ListSerializer(Address.serializer()), it).forEach {
                addresses.add(it)
            }
            counter = (addresses.maxByOrNull { it.id ?: 0 }?.id ?: 0) + 1
        }
    }
}
