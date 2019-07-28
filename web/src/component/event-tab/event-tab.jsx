import React, { PureComponent } from "react";
import { Container, Row, Col } from "reactstrap";

import EventGrid from "component/event-grid/event-grid";

export default class EventTab extends PureComponent {
  getGrid(event, key) {
    if (!event) {
      return null;
    }

    return (
      <Col xs="4">
        <EventGrid key={key} event={event} />
      </Col>
    );
  }

  getRows(events) {
    if (!events || events.length === 0) {
      return null;
    }

    let gridPerRow = 3;
    let rows = [];
    let j = 0;

    for (let i = 0; i < Math.ceil(events.length / gridPerRow); i++) {
      // Every row has three grids
      let row = [];
      for (; j < events.length; ) {
        row.push(this.getGrid(events[j], j));

        if (++j % gridPerRow === 0) {
          // Start a new row
          break;
        }
      }

      rows.push(
        <Container>
          <Row key={i}>{row}</Row>
          <br />
        </Container>
      );
    }

    return rows;
  }

  render() {
    if (!this.props.events) {
      return null;
    }

    return (
      <Container>
        <br />
        {this.getRows(this.props.events)}
      </Container>
    );
  }
}
