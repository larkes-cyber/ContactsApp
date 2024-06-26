package mapper

import kotlinx.serialization.json.Json
import ktor.models.AddTipRequest
import ktor.models.EditTipRequest
import ktor.models.TipResponse
import models.Tip
import models.TipTags
import org.larkes.contacts.database.TipEntity
import uuid.randomUUID

fun TipEntity.toTip():Tip{
    return Tip(
        id = id,
        color = color,
        description = description,
        imageSrc = imageSrc,
        serverId = serverId,
        tags = if(tags != null) Json.decodeFromString<TipTags>(tags!!) else null,
        title = title
    )
}

fun TipResponse.toTipEntity(id:String):TipEntity{
    return TipEntity(
        id = id,
        color = color,
        description = description,
        imageSrc = imageSrc,
        serverId = serverId,
        tags = if(tags != null) Json.encodeToString(TipTags.serializer(), tags) else null,
        title = title
    )
}
fun TipResponse.toTip(id:String):Tip{
    return Tip(
        id = id,
        color = color,
        description = description,
        imageSrc = imageSrc,
        serverId = serverId,
        tags = tags,
        title = title
    )
}

fun Tip.toTipEntity(id:String):TipEntity{
    return TipEntity(
        id = id,
        color = color,
        description = description,
        imageSrc = imageSrc,
        serverId = serverId,
        tags = if(tags != null) Json.encodeToString(TipTags.serializer(), tags!!) else null,
        title = title
    )
}

fun Tip.toEditTipRequest():EditTipRequest{
    return EditTipRequest(
        title = title,
        description = description,
        imageSrc = imageSrc,
        color = color,
        serverId = serverId!!,
        tags = tags
    )
}

fun Tip.toAddTipRequest():AddTipRequest{
    return AddTipRequest(
        title, description, imageSrc, tags, color
    )
}