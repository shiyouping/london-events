import React, { PureComponent, Fragment } from "react";
import { Card, CardText, CardBody, CardTitle } from "reactstrap";

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

    return (
      <Fragment>
        <Card>
          <CardBody>
            <img src={imageUrl} max-width="285" height="285" alt={title} />
          </CardBody>
          <CardBody>
            <CardTitle>Title: {title}</CardTitle>
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
