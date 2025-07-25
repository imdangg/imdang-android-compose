package info.imdang.imdang.core.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class SeoulAreaPreferencesSerializer @Inject constructor() : Serializer<SeoulDistrictPreferences> {
    override val defaultValue: SeoulDistrictPreferences =
        SeoulDistrictPreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): SeoulDistrictPreferences =
        try {
            SeoulDistrictPreferences.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }

    override suspend fun writeTo(t: SeoulDistrictPreferences, output: OutputStream) {
        t.writeTo(output)
    }
}