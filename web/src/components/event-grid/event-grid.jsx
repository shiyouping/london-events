import React, { PureComponent, Fragment } from "react";
import { Card, CardText, CardBody, CardTitle } from "reactstrap";

import ImagePlaceholder from "components/event-grid/image-placeholder.jpg";

export default class EventInfo extends PureComponent {
  render() {
    if (!this.props.event) {
      return null;
    }

    let {
      imageUrl,
      title,
      startTime,
      venueAddress,
      weather,
      city
    } = this.props.event;

    if (!imageUrl) {
      imageUrl = ImagePlaceholder;
    }

    return (
      <Fragment>
        <Card>
          <CardBody>
            <img src={imageUrl} max-width="285" height="285" alt={title} />
          </CardBody>
          <CardBody>
            <CardTitle>{title}</CardTitle>
            <CardText>Time: {startTime}</CardText>
            <CardText>City: {city}</CardText>
            <CardText>Venue: {venueAddress}</CardText>
            <CardText>Weather: {weather}</CardText>
          </CardBody>
        </Card>
      </Fragment>
    );
  }
}
