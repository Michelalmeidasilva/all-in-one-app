import android.app.Application
import com.michel.galileu.data.room.DatabaseApp
import com.michel.galileu.data.room.entities.ProductCategoryEntity
import com.michel.galileu.data.room.getDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductCategoryRepository(application: Application) {
    private val databaseApp: DatabaseApp = getDatabase(application)

    suspend fun insertCategory(value: ProductCategoryEntity) {
        withContext(Dispatchers.IO) {
            databaseApp.productCategoryDao().insert(value)
        }
    }

    suspend fun getCategoryById(id: Int): ProductCategoryEntity {
        var data: ProductCategoryEntity;
        withContext(Dispatchers.IO) {
            data = databaseApp.productCategoryDao().getById(id);
        }
        return data;
    }

    suspend fun getCategories(): List<ProductCategoryEntity> {
        var data: List<ProductCategoryEntity>;

        withContext(Dispatchers.IO) {
            data = databaseApp.productCategoryDao().getAll();
        }

        return data;
    }


    suspend fun removeCategory(values: List<ProductCategoryEntity>) {
        withContext(Dispatchers.IO) {
            values.forEach {
                databaseApp.productCategoryDao().delete(it)
            }
        }
    }

    suspend fun updateCategory(value: ProductCategoryEntity) {
        withContext(Dispatchers.IO) {
            databaseApp.productCategoryDao().update(value)
        }
    }
}