package io.github.overlordsiii.fixairblocks.mixin;

import java.util.function.Predicate;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.server.command.CloneCommand;

@Mixin(CloneCommand.class)
public class CloneCommandMixin {


	@Redirect(method = "execute", at = @At(value = "INVOKE", target = "Ljava/util/function/Predicate;test(Ljava/lang/Object;)Z"))
	private static boolean allowAirBlocks(Predicate<CachedBlockPosition> predicate, Object t) {
		CachedBlockPosition test = (CachedBlockPosition) t;
		return predicate.test(test) && !test.getBlockState().isAir();
	}
}
