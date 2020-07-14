package carpet.commands;

import carpet.utils.CarpetProfiler;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.ServerCommandSource;

import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;
import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

/**
 * 服务器关闭了tick指令 但又想用到tick entities 的功能 
 * 所以直接创建一个专属于splink服的指令
 *
 * @author Acyco
 * @create 2020-07-14 10:51
 * @url https://acyco.cn
 */
public class SPLCommand {
//    public static final String COMMAND_TICK_ENTITIES = "te";
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("te")
                .executes((c) -> healthEntities(c.getSource(), 100))
                .then(argument("ticks", integer(20,24000))
                        .executes((c) -> healthEntities(c.getSource(), getInteger(c, "ticks"))))
        );
    }

    public static int healthEntities(ServerCommandSource source, int ticks)
    {
        CarpetProfiler.prepare_entity_report(source, ticks);
        return 1;
    }
}
