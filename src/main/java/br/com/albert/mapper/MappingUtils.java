package br.com.albert.mapper;

//import java.lang.reflect.Constructor;
//import java.lang.reflect.InvocationTargetException;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.hibernate.collection.spi.PersistentCollection;
//import org.modelmapper.ModelMapper;
//import org.modelmapper.config.Configuration;
//import org.springframework.data.mapping.MappingException;
//
//import br.com.albert.dto.DTO;

public class MappingUtils {
	/*
	public <T extends DTO> ModelMapper getMapper(Class<T> target) throws Throwable {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
				.setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
				.setFieldMatchingEnabled(true)
				.setPropertyCondition(context -> !(context.getSource() instanceof PersistentCollection));
		return updateMapping(modelMapper, target);
	}

	public <T extends DTO>ModelMapper updateMapping(ModelMapper mapper, Class<T> dto) throws Throwable {
		try {
			Constructor<T> constructor = dto.getConstructor();
			T instance = constructor.newInstance();
			return instance.updateModelMapper(mapper, this);
		}catch (NoSuchMethodError e) {
			throw new MappingException(dto.getName());
		}
	}
	
	
	public <S, T extends DTO> List<T> mapList(List<S> source, Class<T> target) throws Throwable{
		
		ModelMapper modelMapper = getMapper(target);
		
		return source.stream().map(el -> modelMapper.map(el, target)).collect(Collectors.toList());
		
	}
	
	public T map(S source, Class<T> target) {
		ModelMapper mapper = getMapper(target);
		
		return mapper.map(source, target);
	}
*/
}
