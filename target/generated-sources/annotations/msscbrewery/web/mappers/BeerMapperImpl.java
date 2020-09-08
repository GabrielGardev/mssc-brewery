package msscbrewery.web.mappers;

import javax.annotation.processing.Generated;
import msscbrewery.domain.Beer;
import msscbrewery.domain.Beer.BeerBuilder;
import msscbrewery.web.model.BeerDto;
import msscbrewery.web.model.BeerDto.BeerDtoBuilder;
import msscbrewery.web.model.v2.BeerStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-08T16:32:10+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 14.0.1 (Oracle Corporation)"
)
@Component
public class BeerMapperImpl implements BeerMapper {

    @Autowired
    private DateMapper dateMapper;

    @Override
    public BeerDto beerToBeerDto(Beer beer) {
        if ( beer == null ) {
            return null;
        }

        BeerDtoBuilder beerDto = BeerDto.builder();

        beerDto.id( beer.getId() );
        beerDto.beerName( beer.getBeerName() );
        if ( beer.getBeerStyle() != null ) {
            beerDto.beerStyle( beer.getBeerStyle().name() );
        }
        beerDto.upc( beer.getUpc() );
        beerDto.createdDate( dateMapper.asOffsetDateTime( beer.getCreatedDate() ) );
        beerDto.lastUpdatedDate( dateMapper.asOffsetDateTime( beer.getLastUpdatedDate() ) );

        return beerDto.build();
    }

    @Override
    public Beer beerDtoToBeer(BeerDto dto) {
        if ( dto == null ) {
            return null;
        }

        BeerBuilder beer = Beer.builder();

        beer.id( dto.getId() );
        beer.beerName( dto.getBeerName() );
        if ( dto.getBeerStyle() != null ) {
            beer.beerStyle( Enum.valueOf( BeerStyle.class, dto.getBeerStyle() ) );
        }
        beer.upc( dto.getUpc() );
        beer.createdDate( dateMapper.asTimestamp( dto.getCreatedDate() ) );
        beer.lastUpdatedDate( dateMapper.asTimestamp( dto.getLastUpdatedDate() ) );

        return beer.build();
    }
}
