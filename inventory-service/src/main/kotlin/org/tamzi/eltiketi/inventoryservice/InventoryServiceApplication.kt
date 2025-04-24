package org.tamzi.eltiketi.inventoryservice

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class InventoryServiceApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(InventoryServiceApplication::class.java, *args)
        }
    }
}
